import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';
import {  Navigation, Body, Title, select } from "./containers";
import AppNavbar from './AppNavbar';
import { AppNavigation } from "./AppNavigation";
import './createBlog.css';
import styled from "styled-components";
import {
  AppContainer as BaseAppContainer
} from "./containers";

const AppContainer = styled(BaseAppContainer)`
  height: calc(100vh - 40px);
`;




class createBlog extends Component {

static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };

  constructor(props) {
    super(props);
    const {cookies} = props;
    this.state = {cats: [], csrfToken: cookies.get('XSRF-TOKEN'), isLoading: true};
    
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('/api/categories', {credentials: 'include'})
      .then(response => response.json())
      .then(data => this.setState({cats: [{id: '', name: 'Choose a category'}].concat(data), isLoading: false}))
      .catch(() => this.props.history.push('/'));
  }
  
    onDropdownSelected(e) {
    console.log("THE VAL", e.target.value);
    //here you will see the current selected value of the select input
}

  render() {
    const {cats, isLoading} = this.state;
    

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
        <div >
        <AppNavbar />  
        <AppContainer>          
        <Navigation>
          <h2></h2>
          <AppNavigation />
        </Navigation>
      
        <Body className="Blog-body" >
          <Title><h2>Create New Blog</h2> </Title>
          <form>
        <select>
          {cats.map((group) => <option key={group.id} >{group.name}</option>)}
        </select> 

        </form>
        </Body> 
        </AppContainer>                  
        </div>
    );
}
}

export default withCookies(withRouter(createBlog));