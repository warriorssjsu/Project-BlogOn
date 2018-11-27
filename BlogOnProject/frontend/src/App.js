import React, { Component } from 'react';
import { CookiesProvider } from 'react-cookie';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import BlogEdit from './BlogEdit';
import BlogList from './BlogList';
import TopBlogList from './TopBlogList';
import AllBlogList from './AllBlogList';
import ManageBlogList from './ManageBlogList';
import Settings from './Settings';

class App extends Component {
  render() {
    return (
     <CookiesProvider>
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/home' exact={true} component={Home}/>
          <Route path='/login' exact={true} component={Home}/>          
          <Route path='/blogs' exact={true} component={BlogList}/>
          <Route path='/topblogs' exact={true} component={TopBlogList}/>
          <Route path='/allblogs' exact={true} component={AllBlogList}/>
          <Route path='/manageblogs' exact={true} component={ManageBlogList}/>
          <Route path='/blogs/:id' component={BlogEdit}/>
          <Route path='/settings' component={Settings}/>
          
        </Switch>
      </Router>
      </CookiesProvider>
    );
  }
}

export default App;
