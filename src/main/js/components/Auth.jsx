import React from "react";

export class SignOut extends React.Component {
	constructor(props){
		super(props)

		this.handleSignout = this.handleSignout.bind(this)
	}

	handleSignout() {
		//todo: add call to backend to remove user
		window.localStorage.removeItem('user');
		location.reload();
	}

	render(){
		return (
			<button type="button" className="btn btn-danger" onClick={this.handleSignout}>Change name</button>
		);
	}
}

export class SignIn extends React.Component {
	
	constructor(props) {
		super(props)
		this.state = { value: '' }

		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleChange(event) {
		this.setState({ value: event.target.value });
	}

	handleSubmit(event) {
		event.preventDefault;
		window.localStorage.setItem('user', this.state.value);
		location.reload();	
	}

	render() {
		return (
			<div id="signin-wrapper" className="text-center">
				<form className="form-signin" onSubmit={this.handleSubmit}>

					<label htmlFor="inputEmail" className="sr-only">Set user id</label>
					<input type="text" minLength="5" id="inputName" className="form-control" placeholder="User ID" value={this.state.value} onChange={this.handleChange} required autoFocus />

					<button className="btn btn-lg btn-primary btn-block" type="submit">GO</button>

				</form>
			</div>
		)
	}

}
