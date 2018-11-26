import React, { Component } from 'react';

import { Button, ButtonGroup } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link, withRouter } from 'react-router-dom';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';

import {  Navigation, Body, Title } from "./containers";

import { AppNavigation } from "./AppNavigation";

import './createBlog.css';

import styled from "styled-components";
import {
  AppContainer as BaseAppContainer
} from "./containers";

const AppContainer = styled(BaseAppContainer)`
  height: calc(100vh - 40px);
`;

class BlogList extends Component {
   static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };
  constructor(props) {
    super(props);
    const {cookies} = props;
    this.state = {blogs: [], csrfToken: cookies.get('XSRF-TOKEN'), isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/blogs', {credentials: 'include'})
      .then(response => response.json())
      .then(data => this.setState({blogs: data, isLoading: false}))
      .catch(() => this.props.history.push('/'));
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
            <Button size="sm" color="primary" tag={Link} to={"/blogs/" + blog.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => {if(window.confirm('Delete the blog?')) {this.remove(blog.id)};}}>Delete</Button>
          </ButtonGroup></div>
        </div>
    });

    return (
      <div>
      <AppNavbar/>
      <AppContainer>
        <Navigation>
          <h2></h2>
          <AppNavigation />
        </Navigation>
        <Body className="Blog-body">
          
          <Title><h2>My Blogs</h2></Title>
          
            {blogList}
            
        </Body>
      </AppContainer>
    </div>
    );
  }
}

export default withCookies(withRouter(BlogList));