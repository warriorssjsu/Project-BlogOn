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

class AllBlogList extends Component {
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
    this.remove = this.remove.bind(this);
   
  }

  async componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/blogs', {credentials: 'include'})
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

  async remove(id) {
    await fetch(`/api/blog/${id}`, {
      method: 'DELETE',
      headers: {
        'X-XSRF-TOKEN': this.state.csrfToken,
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      credentials: 'include'
    }).then(() => {
      let updatedBlogs = [...this.state.blogs].filter(i => i.id !== id);
      this.setState({blogs: updatedBlogs});
    });
  }



  render() {
    const {blogs, isLoading} = this.state;
    const {item} = this.state;
    if (isLoading) {
      return <p>Loading...</p>;
    }
    
    
    const blogList = blogs.map(blog => {

      const blogDesc = ` ${blog.description || ''} `;
      return <div>
        <h3 style={{whiteSpace: 'nowrap'}}>{blog.title} ({blog.category})</h3>
        <div>{blogDesc}</div>  
           
          <div style={{float:'right'}} >
          <ButtonGroup>
            <Button size="sm" style={{marginLeft:1}} color="danger" onClick={() => {if(window.confirm('Delete the blog?')) {this.remove(blog.id)};}}>Delete</Button>
          </ButtonGroup></div>
          <hr />
        </div>
        
    });

    return (
        ('Admin' !== item.role)? 
        <div>
      <AppNavbar/>
      <AppContainer>
        <Navigation>
          <h2></h2>
          <SideNavigation />
        </Navigation>
        <Body className="Blog-body">
        <Title><h2>Manage Blogs </h2></Title>
        <h2>You dont have authorization to this link. Please contact admin at pooja.agarwal@sjsu.edu</h2>
        </Body>
      </AppContainer>
    </div>:
      <div>
      <AppNavbar/>
      <AppContainer>
        <Navigation>
          <h2></h2>
          <SideNavigation />
        </Navigation>
        <Body className="Blog-body">
          
          <Title><h2>Manage Blogs </h2></Title>
            {blogList}
            
        </Body>
      </AppContainer>
    </div>
    );
  }
}

export default withCookies(withRouter(AllBlogList));