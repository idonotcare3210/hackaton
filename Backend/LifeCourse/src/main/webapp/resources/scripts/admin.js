const contextPath = mainData.dataset.contextPath;

const allRoles = { };
for (const roleData of allRolesData.querySelectorAll('data')) {
	const role = {
		id: +roleData.dataset.id,
		name: roleData.dataset.name,
		readableName: roleData.innerText
	};
	allRoles[role.id] = role;
}

function closeDialog() {
	addRoleDialog.hidden = true;
	removeRoleDialog.hidden = true;
}
addRoleDialogClose.onclick =    ev => {
	closeDialog();
	ev.preventDefault();
};
removeRoleDialogClose.onclick = ev => {
	closeDialog();
	ev.preventDefault();
};

function showAddDialog(user) {
	closeDialog();
	addRoleDialog.hidden = false;
	addRoleDialog.action = `${contextPath}/admin/addRole/${user.id}`;
	addRoleDialog.querySelector('span.username').innerText = user.username;
	addRoleDialog.querySelector('input[name="userId"]').value = user.id;
	/** @type {HTMLSelectElement} */
	const select = addRoleDialog.querySelector('select');
	select.innerHTML = '';
	for (const roleId in allRoles) {
		const role = allRoles[roleId];
		if (user.roles.includes(role.id) || role.name == "ROLE_GUEST" || role.name == "ROLE_ADMIN") continue;
		select.innerHTML += `<option value="${role.id}">${role.name}</option>`;
	}
}
function showRemoveDialog(user) {
	closeDialog();
	removeRoleDialog.hidden = false;
	removeRoleDialog.action = `${contextPath}/admin/removeRole/${user.id}`;
	removeRoleDialog.querySelector('span.username').innerText = user.username;
	removeRoleDialog.querySelector('input[name="userId"]').value = user.id;
	/** @type {HTMLSelectElement} */
	const select = removeRoleDialog.querySelector('select');
	select.innerHTML = '';
	for (const roleId of user.roles) {
		const role = allRoles[roleId];
		if (role.name == "ROLE_GUEST" || role.name == "ROLE_ADMIN") continue;
		select.innerHTML += `<option value="${role.id}">${role.name}</option>`;
	}
}

const users = { };
/** @type {HTMLTableElement} */
for (const row of usersTable.tBodies[0].rows) {
	// users and roles
	const roles = [];
	for (const roleData of row.querySelectorAll('data.role')) {
		roles.push(+roleData.dataset.id);
	}
	const user = {
		id: row.dataset.id,
		username: row.dataset.username,
		roles
	};
	users[user.id] = user;

	// buttons
	const
		addRole = row.querySelector('button.buttonAddRole'),
		removeRole = row.querySelector('button.buttonRemoveRole');
	addRole.onclick = ev => showAddDialog(user);
	removeRole.onclick = ev => showRemoveDialog(user);
}
