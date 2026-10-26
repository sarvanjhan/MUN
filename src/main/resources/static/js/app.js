(function () {
  var shell = document.querySelector('.shell');
  if (shell) {
    var toggle = function () { shell.classList.toggle('open'); };
    document.querySelectorAll('[data-nav-toggle]').forEach(function (el) { el.addEventListener('click', toggle); });
  }
  // Client-side table filter: <input data-filter="#tableId">
  document.querySelectorAll('input[data-filter]').forEach(function (input) {
    var table = document.querySelector(input.getAttribute('data-filter'));
    if (!table) return;
    var empty = document.querySelector(input.getAttribute('data-empty') || '');
    input.addEventListener('input', function () {
      var q = input.value.trim().toLowerCase(), shown = 0;
      table.querySelectorAll('tbody tr[data-row]').forEach(function (tr) {
        var hit = tr.textContent.toLowerCase().indexOf(q) !== -1;
        tr.style.display = hit ? '' : 'none';
        if (hit) shown++;
      });
      if (empty) empty.style.display = shown === 0 ? 'block' : 'none';
    });
  });
})();
