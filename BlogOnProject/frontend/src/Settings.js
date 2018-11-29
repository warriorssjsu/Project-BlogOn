import React, { Component } from 'react';

import { Table, Button, ButtonGroup } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link, withRouter } from 'react-router-dom';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';
import { Form, FormGroup, Input, Label } from 'reactstrap';

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
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    
   
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

async handleSubmit(event) {
    event.preventDefault();
    const {profile, csrfToken} = this.state;

    await fetch('/api/profile', {
      method: 'POST',
      headers: {
         'X-XSRF-TOKEN': csrfToken,
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(profile),
      credentials: 'include'
    });
    this.props.history.push('/savedProfile');
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const title = target.name;
    let profile = {...this.state.profile};
    profile[title] = value;
    this.setState({profile});
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
      <AppNavbar/>
      <AppContainer>
        <Navigation>
          <h2></h2>
          <SideNavigation />
        </Navigation>
        <Body className="Blog-body">
          
          <Title><h2>Profile Settings </h2></Title>
          <Table>
            <tr><td>ID:</td> <td>{item.id}</td></tr>
            
            <tr><td>Name:</td> <td>{item.name}</td></tr>
             <tr><td>Email:</td> <td>{item.email}</td></tr>
              <tr><td>Role:</td> <td>{item.role}</td></tr>

        </Table>
        <Form onSubmit={this.handleSubmit}>
        
          <FormGroup>
            <Label for="displayName">Display Name</Label>
            <Input type="text" name="displayName" id="displayName" value={profile.displayName || ''}
             onChange={this.handleChange}
                    autoComplete="displayName"/>
          </FormGroup>
          <FormGroup>
            <Label for="secEmail">Secondary Email</Label>
            <Input type="text" name="secEmail" id="secEmail" value={profile.secEmail || ''}
             onChange={this.handleChange}
                    autoComplete="secEmail"/>
          </FormGroup>
          <FormGroup>
            <Label for="address">Address</Label>
            <Input type="text" name="address" id="address" value={profile.address || ''}
             onChange={this.handleChange}
                   autoComplete="address"/>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/">Cancel</Button>
          </FormGroup>
        </Form>
        
            
        </Body>
      </AppContainer>
    </div>
    );
  }
}

export default withCookies(withRouter(Settings));