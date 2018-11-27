import React, { Component } from 'react';

import { Button, ButtonGroup } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link, withRouter } from 'react-router-dom';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';

import {  Navigation, Body, Title } from "./containers";

import SideNavigation from "./SideNavigation";

import './createBlog.css';

import styled from "styled-components";
import {
  AppContainer as BaseAppContainer
} from "./containers";

const AppContainer = styled(BaseAppContainer)`
  height: calc(100vh - 40px);
`;

class Settings extends Component {
   static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };
  emptyItem = {
    id:'',
    name: '',
    email: '',
    role: ''
  };
  constructor(props) {
    super(props);
    const {cookies} = props;
    this.state = {blogs: [], 
      item: this.emptyItem, 
      csrfToken: cookies.get('XSRF-TOKEN'), 
      isLoading: true};
    
   
  }

  async componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/myblogs', {credentials: 'include'})
      .then(response => response.json())
      .then(data => this.setState({blogs: data, isLoading: false}))
      .catch(() => this.props.history.push('/'));

      try {
      const user = await (await fetch(`/api/role`,{credentials: 'include'})).json();
      this.setState({item: user});
    } catch (error) {
        this.props.history.push('/');
      }

  
  }

  render() {
    const {blogs, isLoading} = this.state;
    const {item} = this.state;
    if (isLoading) {
      return <p>Loading...</p>;
    }

       


    return (
      <div>
      <AppNavbar/>
      <AppContainer>
        <Navigation>
          <h2></h2>
          <SideNavigation />
        </Navigation>
        <Body className="Blog-body">
          
          <Title><h2>Settings </h2></Title>
            
        </Body>
      </AppContainer>
    </div>
    );
  }
}

export default withCookies(withRouter(Settings));