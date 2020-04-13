const wrapper = document.querySelector("#users-wrapper");


function fetchUsers() {
//  fetch("http://localhost:8080/web-socket/users")
  fetch("https://the-communicator.herokuapp.com/web-socket/users")
      .then(data => data.json())
      .then(jsonData => populate(jsonData))
      .catch(e => {
          wrapper.innerText = "Error: "+e+" going to use demo data";
          populate(demoData); //remove me
      });
};


function dom(tag, text) {
    let r = document.createElement(tag);
    if (text) r.innerText = text;
    return r;
};

function append(parent, child) {
  parent.appendChild(child);
  return parent;
};

function populate(json) {
    if (json.length === 0) return;
    let keys = Object.keys(json[0]);
    let table = dom('table');
    //header
    append(table,
      keys.map(k => dom('th', k)).reduce(append, dom('tr'))
    );
    //values
    const makeRow = (acc, row) =>
        append(acc,
            keys.map(k => dom('td', row[k])).reduce(append, dom('tr'))
        );
    json.reduce(makeRow, table);
    while (wrapper.firstChild) {
        wrapper.removeChild(wrapper.lastChild);
      }
    wrapper.appendChild(table);
};