const id = new URLSearchParams(window.location.search).get('id');

async function loadReport() {
  try {
    const res = await fetch(`/api/reports/${id}`);
    if (!res.ok) throw new Error("Запись не найдена");
    const report = await res.json();

    document.getElementById('date').value = report.date;
    document.getElementById('job').value = report.job;
    document.getElementById('amount').value = report.amount;
    document.getElementById('coef').value = report.coef;
    document.getElementById('weekendCoef').value = report.weekendCoef;
  } catch (err) {
    alert("Ошибка загрузки отчёта: " + err.message);
  }
}

document.getElementById('editForm').addEventListener('submit', async (e) => {
  e.preventDefault();

  const updatedReport = {
    date: document.getElementById('date').value,
    job: document.getElementById('job').value,
    amount: parseFloat(document.getElementById('amount').value),
    coef: parseFloat(document.getElementById('coef').value),
    weekendCoef: parseFloat(document.getElementById('weekendCoef').value)
  };

  try {
    const res = await fetch(`/api/reports/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedReport)
    });

    if (res.ok) {
      alert('Отчёт обновлён');
      window.location.href = 'profile.html';
    } else {
      alert('Ошибка обновления');
    }
  } catch (err) {
    alert("Ошибка при сохранении: " + err.message);
  }
});

loadReport();
