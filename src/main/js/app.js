const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

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
		return (
			<UsersList users={this.state.users}/>
		)
	}
}

class UsersList extends React.Component {
	render() {
		console.log('props', this.props);
		const users = this.props.users.map(user => <User user={user}/>)

		return (
			<table>
				<thead>
					<tr>
						<th>ID</th>
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
	render(){
		return (
			<tr>
				<td>{this.props.user.id}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
