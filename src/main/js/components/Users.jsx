import React from "react";

export class UsersList extends React.Component {
	constructor(props) {
		super(props);
		this.state = {users: []};
	}

	// TODO: Try to use async/await
	componentDidMount() {
		fetch("/api/web-socket/users")
			.then(data => data.json())
			.then(users => {
				console.log("users", users);
				this.setState({users: users});
			})
	}		

	render() {
		console.log('usersList state', this.state);
		const users = this.state.users.map(user => <User key={user.id} user={user} />)

		return (
			<table className="table">
				<thead>
					<tr>
						<th scope="col">ID</th>
					</tr>
				</thead>
				<tbody>
					{users}
				</tbody>
			</table>
		)
	}
}

class User extends React.Component {
	render() {
		return (
			<tr>
				<td>{this.props.user.id}</td>
			</tr>
		)
	}
}
