const executorName = 'Sergey'; // TODO

async function loadReports() {
  const from = document.getElementById('dateFrom').value;
  const to = document.getElementById('dateTo').value;

  const url = `/api/reports/user/${executorName}` +
    (from && to ? `?from=${from}&to=${to}` : '');

  try {
    const res = await fetch(url);
    const reports = await res.json();

    let total = 0;
    const tbody = document.querySelector('#reportsTable tbody');
    tbody.innerHTML = '';

    reports.forEach(r => {
      const salary = r.amount * r.coef * r.weekendCoef; //TODO
      total += salary;

      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${r.date}</td>
        <td><a href="edit.html?id=${r.id}">${r.job}</a></td>
        <td>${salary.toFixed(2)} ₽</td>
        <td>
          <button onclick="deleteReport(${r.id})">Удалить</button>
        </td>
      `;
      tbody.appendChild(row);
    });

    document.getElementById('executorName').textContent = executorName;
    document.getElementById('totalSalary').textContent = total.toFixed(2) + ' ₽';

  } catch (err) {
    alert('Ошибка при загрузке: ' + err.message);
  }
}

async function deleteReport(id) {
  if (!confirm("Удалить этот отчёт?")) return;

  try {
    const res = await fetch(`/api/reports/${id}`, { method: 'DELETE' });
    if (res.ok) {
      alert('Отчет удалён');
      loadReports();
    } else {
      alert('Ошибка удаления');
    }
  } catch (err) {
    alert('Ошибка при удалении: ' + err.message);
  }
}
window.loadReports = loadReports;
window.deleteReport = deleteReport;
window.addEventListener('DOMContentLoaded', loadReports);