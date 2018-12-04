import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { instanceOf } from 'prop-types';
import { Cookies, withCookies } from 'react-cookie';
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

class BlogEdit extends Component {
  static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };
  emptyItem = {
    title: '',
    description: '',
    category: ''
  };

  constructor(props) {
    super(props);
    const {cookies} = props;
    this.state = {
      item: this.emptyItem,
      csrfToken: cookies.get('XSRF-TOKEN')
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      try {
      const blog = await (await fetch(`/api/blog/${this.props.match.params.id}`,{credentials: 'include'})).json();
      this.setState({item: blog});
    } catch (error) {
        this.props.history.push('/');
      }
    }

  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const title = target.name;
    let item = {...this.state.item};
    item[title] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item, csrfToken} = this.state;

    await fetch('/api/blog', {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
         'X-XSRF-TOKEN': csrfToken,
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
      credentials: 'include'
    });
    this.props.history.push('/blogs');
  }

  render() {
    const {item} = this.state;
    const data = [{id: '1', name: 'Art'}, {id: '2', name: 'Technology'},
    {id: '3', name: 'Travel'}, {id: '4', name: 'Science'},
    {id: '5', name: 'Social Life'}, {id: '6', name: 'Festivals'},
    {id: '7', name: 'Culture'},{id: '8', name: 'Other'}]
     
    const cats = [{id: '', name: 'Choose a category'}].concat(data);
    const title = <h2>{item.id ? 'Edit Blog' : 'Create Blog'}</h2>;

    return <div>
      <AppNavbar/>
      <AppContainer>
        <Navigation>
          <h2></h2>
          <SideNavigation />
        </Navigation>
        <Body className="Blog-body">
        <Title>{title}</Title>
        
        <Form onSubmit={this.handleSubmit}>
        
        <FormGroup>
          
        <select name="category" id="category" value={item.category || ''}
             onChange={this.handleChange}   autoComplete="category" >
          {cats.map((group) => <option value={group.name} key={group.id} >{group.name}</option>)}
        </select> 
        </FormGroup>
          <FormGroup>
            <Label for="title">Title</Label>
            <Input type="text" name="title" id="title" value={item.title || ''}
                   onChange={this.handleChange} autoComplete="title"/>
          </FormGroup>
          <FormGroup>
            <Label for="description">Description</Label>
            <Input type="textarea" name="description" id="description" value={item.description || ''}
                   onChange={this.handleChange} autoComplete="description"/>
          </FormGroup>
        
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/">Cancel</Button>
          </FormGroup>
        </Form>
        </Body>
      </AppContainer>
    </div>
  }
}

export default withCookies(withRouter(BlogEdit));