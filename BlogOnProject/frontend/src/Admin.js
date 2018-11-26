import React, { Component } from 'react';
import './App.css';
import { Link } from 'react-router-dom';
import { Button } from 'reactstrap';
import { withCookies } from 'react-cookie';
import './Home.css';
import 'bootstrap/dist/css/bootstrap.min.css';


import {  Navigation, Body } from "./containers";
import { Route, Switch } from 'react-router-dom';

import BlogList from './BlogList';
import BlogEdit from './BlogEdit';

import AppNavbar from './AppNavbar';
import SideNavigation from "./SideNavigation";

import styled from "styled-components";
import {
  AppContainer as BaseAppContainer
} from "./containers";

const AppContainer = styled(BaseAppContainer)`
  height: calc(100vh - 40px);
`;

class Admin extends Component {
  state = {
    isLoading: true,
    isAuthenticated: false,
    user: undefined
  };


  constructor(props) {
    super(props);
    const {cookies} = props;
    this.state.csrfToken = cookies.get('XSRF-TOKEN');

  }

  async componentDidMount() {
    const response = await fetch('/api/admin', {credentials: 'include'});
    const body = await response.text();
    if (body === '') {
      this.setState(({isAuthenticated: false}))
    } else {
      this.setState({isAuthenticated: true, user: JSON.parse(body)})
    }
  }

  render() {
  

      const message = this.state.user ?
      <div>        
        <AppContainer>          
        <Navigation>
          <h2></h2>
          <SideNavigation />
        </Navigation>          
          <Body >
            <h2>Welcome admin, {this.state.user.name}!</h2>
            <Button color="none"><Link to="/blogs/new">Create New Blog</Link></Button>
          </Body>
      
      </AppContainer>
                
      </div > :
      <Body className="Home-div">
      <h2>Share your experiences with us, with all</h2>
      <p>Create free blogs, read interesting blogs and tell us how you feel about it.</p>
      
      </Body>;
    
    
    return (   
      <div>
      
        <AppNavbar />     
             {message}         
           
          
      </div>

    );  
  }
  
}


export default withCookies(Admin);