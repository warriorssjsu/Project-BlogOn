import React, { Component } from 'react';
//import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
//import { Link } from 'react-router-dom';
import { Button } from 'reactstrap';
import { Link } from 'react-router-dom';
import { withCookies } from 'react-cookie';
import './AppNavbar.css'
import 'bootstrap/dist/css/bootstrap.min.css';
class AppNavbar extends Component {
 state = {
    isLoading: true,
    isAuthenticated: false,
    user: undefined
  };
  

  constructor(props) {
    super(props);
    const {cookies} = props;
    this.state.csrfToken = cookies.get('XSRF-TOKEN');
    this.login = this.login.bind(this);
    this.logout = this.logout.bind(this);
  }

  async componentDidMount() {
    const response = await fetch('/api/user', {credentials: 'include'});
    const body = await response.text();
    if (body === '') {
      this.setState(({isAuthenticated: false}))
    } else {
      this.setState({isAuthenticated: true, user: JSON.parse(body)})
    }
  }

  login() {
    let port = (window.location.port ? ':' + window.location.port : '');
    if (port === ':3000') {
      port = ':8080';
    }
    window.location.href = '//' + window.location.hostname + port + '/private';
  }

  logout() {
    fetch('/api/logout', {method: 'POST', credentials: 'include',
      headers: {'X-XSRF-TOKEN': this.state.csrfToken}}).then(res => res.json())
      .then(response => {
        window.location.href = response.logoutUrl + "?id_token_hint=" +
          response.idToken + "&post_logout_redirect_uri=" + window.location.origin;
      });
  }

  render() {    
      

    const button = this.state.isAuthenticated ?
    <div className="AppNav-header">
        <Button color="none"><Link to="/">BlogOn</Link></Button>
        <Button color="none" onClick={this.logout}>Logout</Button>
      </div> :
      <div className="AppNav-header">
      <Button color="none">BlogOn</Button>
      <Button color="none" onClick={this.login}>Login</Button>
      </div>;

 
    return (
    <div>
        
        {button}
        
        </div>
    );
  }
}

export default withCookies(AppNavbar);