import React, { Component } from 'react';

import { Button, ButtonGroup } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { withRouter } from 'react-router-dom';
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

class TopBlogList extends Component {
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
    this.handleLike = this.handleLike.bind(this);
    //this.handleShare = this.handleShare.bind(this);
   
  }

  async componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/topblogs', {credentials: 'include'})
      .then(response => response.json())
      .then(data => this.setState({blogs: data, isLoading: false}))
      .catch(() => this.props.history.push('/'));

  
  }

 async handleLike(id) {
    await fetch(`/api/likeblog/${id}`, {
      headers: {
        'X-XSRF-TOKEN': this.state.csrfToken,
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      credentials: 'include'
    });
  }

  async handleShare(id) {
    await fetch(`/api/shareblog/${id}`, {
      headers: {
        'X-XSRF-TOKEN': this.state.csrfToken,
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      credentials: 'include'
    });
  } 


  render() {
    const {blogs, isLoading} = this.state;
   
    if (isLoading) {
      return <p>Loading...</p>;
    }       
    const blogList = blogs.map(blog => {

      const blogDesc = ` ${blog.description || ''} `;
      return <div>
        <h3 style={{whiteSpace: 'nowrap'}}>{blog.title} ({blog.category})</h3>
        <div>{blogDesc}</div>  
           
          <div style={{float:'right'}}>
          
            <ButtonGroup>
            <Button id ="like" size="sm" onClick={() =>{ this.handleLike(blog.id) }} >Like</Button>
            <Button size="sm" style={{marginLeft:1}}  onClick={() =>{ this.handleShare(blog.id)}}>Share</Button>
            </ButtonGroup>
          </div>
         <hr /> 
        </div>
        
    });

    return (
      <div>
      <AppNavbar/>
      <AppContainer>
        <Navigation>
          <h2></h2>
          <SideNavigation />
        </Navigation>
        <Body className="Blog-body">
          
          <Title><h2>Top Blogs </h2></Title>
            {blogList}
            
        </Body>
      </AppContainer>
    </div>
    );
  }
}

export default withCookies(withRouter(TopBlogList));