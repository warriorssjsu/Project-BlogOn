import React, { Component } from 'react';
import './App.css';
//import { Link } from 'react-router-dom';
import { Table } from 'reactstrap';
import { withCookies } from 'react-cookie';
import './Home.css';
import 'bootstrap/dist/css/bootstrap.min.css';


import {  Navigation, Body, Title } from "./containers";

import AppNavbar from './AppNavbar';
import SideNavigation from "./SideNavigation";

import styled from "styled-components";
import {
  AppContainer as BaseAppContainer
} from "./containers";

const AppContainer = styled(BaseAppContainer)`
  height: calc(100vh - 40px);
`;

class profileSaved extends Component {
  state = {
    isLoading: true,
    isAuthenticated: false,
    user: undefined
  };

emptyItem = {
    id:'',
    name: '',
    email: '',
    role: ''
  };

    profile = {
    displayName:'',
    dob: '',
    secEmail: '',
    address: ''
  };
  constructor(props) {
    super(props);
    const {cookies} = props;
    
    this.state = {
      item: this.emptyItem, 
      profile:this.profile,
      csrfToken: cookies.get('XSRF-TOKEN'), 
      isLoading: true};

  }

  async componentDidMount() {

    this.setState({isLoading: true});

      try {
      const user = await (await fetch(`/api/role`,{credentials: 'include'})).json();
      this.setState({item: user,isLoading: false});
    } catch (error) {
        this.props.history.push('/');
      }
       const getprofile = await (await fetch(`/api/profile/${this.state.item.id}`,{credentials: 'include'})).json();
      this.setState({profile: getprofile});
  }

  render() {

    const { isLoading } = this.state;
    const { item } = this.state;
    const { profile } = this.state;
    if (isLoading) {
      return <p>Loading...</p>;
    }
       return (   
         
      <div>
        <AppNavbar /> 
        <AppContainer>          
        <Navigation>
          <h2></h2>
          <SideNavigation />
        </Navigation>          
          <Body className="Blog-body">
            <Title><h2>Saved Successully!</h2></Title>
            <Table>
            <tr><td>ID:</td> <td>{item.id}</td></tr>
            
            <tr><td>Name:</td> <td>{item.name}</td></tr>
             <tr><td>Email:</td> <td>{item.email}</td></tr>
              <tr><td>Role:</td> <td>{item.role}</td></tr>
              <tr><td>Display Name:</td> <td>{profile.displayName}</td></tr>
              <tr><td>Secondary Email:</td> <td>{profile.secEmail}</td></tr>
              <tr><td>Address:</td> <td>{profile.address}</td></tr>

        </Table>
          </Body>
      
      </AppContainer>
                
      </div > 
     
); 
  
}
}

export default withCookies(profileSaved);