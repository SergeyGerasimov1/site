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

    const grouped = {};
    reports.forEach(r => {
      const date = r.date;
      if (!grouped[date]) grouped[date] = [];
      grouped[date].push(r);
    });

    tbody.innerHTML = '';
    total = 0;

    Object.entries(grouped).forEach(([date, dailyReports]) => {
      let dayTotal = 0;

      dailyReports.forEach(r => {
        const salary = r.amount * r.coef * r.weekendCoef;
        dayTotal += salary;
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

      // Строка "Итого за день"
      const summaryRow = document.createElement('tr');
      summaryRow.style.fontWeight = 'bold';
      summaryRow.style.backgroundColor = '#f8f8f8';
      summaryRow.innerHTML = `
        <td colspan="2">Итого за ${date}:</td>
        <td>${dayTotal.toFixed(2)} ₽</td>
        <td></td>
      `;
      tbody.appendChild(summaryRow);
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