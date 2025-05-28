const id = new URLSearchParams(window.location.search).get('id');

// Загружаем отчёт и подставляем в форму
async function loadReport() {
  try {
    const res = await fetch(`/api/reports/${id}`);
    if (!res.ok) throw new Error("Запись не найдена");
    const report = await res.json();

    // Заполняем поля
    document.getElementById('executor').value = report.executor;
    document.getElementById('date').value = report.date;
    document.getElementById('object').value = report.object;
    document.getElementById('location').value = report.location;
    document.getElementById('section').value = report.section;
    document.getElementById('job').value = report.job;
    document.getElementById('dismantling').checked = report.dismantling;
    document.getElementById('material').value = report.material;
    document.getElementById('amount').value = report.amount;
    document.getElementById('coef').value = report.coef;
    document.getElementById('weekendCoef').value = report.weekendCoef;
    document.getElementById('note').value = report.note;

  } catch (err) {
    alert("Ошибка загрузки отчёта: " + err.message);
  }
}

// Отправляем весь отчёт
document.getElementById('editForm').addEventListener('submit', async (e) => {
  e.preventDefault();

  const updatedReport = {
    id: parseInt(id), // не обязателен, но можно отправить
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
// Загрузка всех разделов работ при загрузке страницы
async function loadSections() {
  try {
    const res = await fetch('/api/work/sections');
    const sections = await res.json();

    const sectionSelect = document.getElementById('section');
    sectionSelect.innerHTML = '<option value="">Выберите раздел</option>';

    console.log('sections =', sections);
    sections.forEach(s => {
      const option = document.createElement('option');
      option.value = s.id; // важно: сохраняем ID, не имя
      option.textContent = s.name;
      sectionSelect.appendChild(option);
    });
  } catch (err) {
    console.error('Ошибка при загрузке разделов:', err);
  }
}

// Загрузка работ по выбранному разделу
async function loadJobs() {
  const sectionId = document.getElementById('section').value;
  if (!sectionId) return;

  try {
    const res = await fetch(`/api/work/types?sectionId=${sectionId}`);
    const jobs = await res.json();

    const jobSelect = document.getElementById('job');
    jobSelect.innerHTML = '';

    jobs.forEach(j => {
      const option = document.createElement('option');
      option.value = j.name; // или j.id, если будешь сохранять ID
      option.textContent = `${j.name}`;
      jobSelect.appendChild(option);
    });
  } catch (err) {
    console.error('Ошибка при загрузке работ:', err);
  }
}
// При загрузке страницы — загрузить разделы
window.addEventListener('DOMContentLoaded', loadSections);

// При смене раздела — загрузить список работ
document.getElementById('section').addEventListener('change', loadJobs);
loadReport();