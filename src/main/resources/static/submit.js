document.getElementById('reportForm').addEventListener('submit', async function (e) {
  e.preventDefault();

  const data = {
    executor: document.getElementById('executor').value,
    date: document.getElementById('date').value,
    object: document.getElementById('object').value,
    location: document.getElementById('location').value,
    jobId: parseInt(document.getElementById('job').value),
    dismantling: document.getElementById('dismantling').checked,
    material: document.getElementById('material').value,
    amount: parseFloat(document.getElementById('amount').value || 0),
    coef: parseFloat(document.getElementById('coef').value || 1),
    note: document.getElementById('note').value
  };

  try {
    console.log("Отправляемые данные:", JSON.stringify(data, null, 2));
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
      option.value = j.id; // или j.id, если будешь сохранять ID
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