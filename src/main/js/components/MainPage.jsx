import React from "react"

import { UsersList } from './Users.jsx'
import { SignIn, SignOut } from './Auth.jsx'

export class MainPage extends React.Component {
	constructor(props) {
		super(props)
	}

	render() {
		const user = window.localStorage.getItem('user');
		console.log('user', user);
		if(user){
			return (<Inside user={user}></Inside>)	
		} else {
			return (<SignIn></SignIn>)
		}
		
	}
}


class Inside extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div>
                <Whoami user={this.props.user}/>
				<UsersList></UsersList>
				<SignOut></SignOut>
			</div>
		)
	}
}

class Whoami extends React.Component {
    constructor(props){
        super(props)
    }

    render() {
        return (
            <div className="card">
            <div className="card-body">
            <h5 className="card-title">You are:</h5>
            <h6 className="card-subtitle mb-2 text-muted">{this.props.user}</h6>
            </div>
        </div>
        )
    }
}

// const Whoami = (props) => (
//     <div className="card" style="width: 18rem;">
//     <div className="card-body">
//       <h5 className="card-title">You are:</h5>
//       <h6 className="card-subtitle mb-2 text-muted">{props.user}</h6>
//     </div>
//   </div>
// )