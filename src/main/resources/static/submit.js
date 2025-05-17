document.getElementById('reportForm').addEventListener('submit', async function (e) {
  e.preventDefault();

  const data = {
    executor: document.getElementById('executor').value,
    date: document.getElementById('date').value,
    object: document.getElementById('object').value,
    location: document.getElementById('location').value,
    section: document.getElementById('section').value,
    job: document.getElementById('job').value,
    dismantling: document.getElementById('dismantling').checked,
    material: document.getElementById('material').value,
    amount: parseFloat(document.getElementById('amount').value || 0),
    coef: parseFloat(document.getElementById('coef').value || 1),
    weekendCoef: parseFloat(document.getElementById('weekendCoef').value || 1),
    note: document.getElementById('note').value
  };

  try {
    const response = await fetch('/api/reports', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    });

    if (!response.ok) {
      throw new Error(await response.text());
    }

    alert('Отчет успешно отправлен!');
    document.getElementById('reportForm').reset();
  } catch (error) {
    alert('Ошибка при добавлении: ' + error.message);
  }
});
