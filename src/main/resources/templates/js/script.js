let form = document.getElementById("form");
let info = document.getElementById("res");


form.onsubmit = async (e) => {
  e.preventDefault();

  if(form.size1.value === "") return;

  let index = form.selected.selectedIndex;
  let index1 = form.selected1.selectedIndex;

  let typeWarehouse = "";
  typeWarehouse = await fetch(`http://localhost:8080/typeWarehouse/`)
  es = await typeWarehouse.text();
  info.textContent = typeWarehouse;
}