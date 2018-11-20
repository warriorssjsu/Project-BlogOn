import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';
import './Home.css';



class CategoryList extends Component {

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
        <div className="Home-div">
        <select >
          {cats.map((group) => <option key={group.id} >{group.name}</option>)}
        </select>                    
          </div>
    );
}
}

export default withCookies(withRouter(CategoryList));