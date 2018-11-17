import React, { Component } from 'react';
import { CookiesProvider } from 'react-cookie';
import './App.css';
import Home from './Home';
import CategoryList from './CategoryList';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {
  render() {
    return (
     <CookiesProvider>
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/home' exact={true} component={Home}/>
          <Route path='/login' exact={true} component={Home}/>
          <Route path='/categories' exact={true} component={CategoryList}/>
        </Switch>
      </Router>
      </CookiesProvider>
    );
  }
}

export default App;
