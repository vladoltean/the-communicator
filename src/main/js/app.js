import React from "react";
import ReactDOM from "react-dom";
import {
	BrowserRouter as Router,
	Switch,
	Route,
	Link
} from "react-router-dom";

import { MainPage } from './components/MainPage.jsx'

class App extends React.Component {

	render() {
		return (

			<Router>
				<div>
					{/*
				A <Switch> looks through all its children <Route>
				elements and renders the first one whose path
				matches the current URL. Use a <Switch> any time
				you have multiple routes, but you want only one
				of them to render at a time
				*/}
					<Switch>
						<Route exact path="/">
							<MainPage />
						</Route>
						<Route path="/about">
							<About />
						</Route>

					</Switch>
				</div>

			</Router>
		)
	}
}

let About = (props) => (
	<div>
		<h1>ABOUT PAGE</h1>
		<h2>bla bla bla</h2>
	</div>
)





ReactDOM.render(
	<App />,
	document.getElementById('react')
)
