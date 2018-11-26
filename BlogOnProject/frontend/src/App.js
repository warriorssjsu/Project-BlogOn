import React, { Component } from 'react';
import { CookiesProvider } from 'react-cookie';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import BlogEdit from './BlogEdit';
import BlogList from './BlogList';
import Admin from './Admin'

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
          <Route path='/blogs/:id' component={BlogEdit}/>
          <Route path='/admin' exact={true} component={Admin}/>
        </Switch>
      </Router>
      </CookiesProvider>
    );
  }
}

export default App;
