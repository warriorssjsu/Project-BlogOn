import React, { Component } from 'react';
import { CookiesProvider } from 'react-cookie';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Basic } from './Basic';
import BlogEdit from './BlogEdit';
import BlogList from './BlogList';

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
          <Route path='/basic' exact={true} component={Basic}/>
          <Route path='/blogs/:id' component={BlogEdit}/>
        </Switch>
      </Router>
      </CookiesProvider>
    );
  }
}

export default App;
