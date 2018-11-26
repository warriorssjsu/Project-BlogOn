import React from "react";
import styled from "styled-components";
import { SideNav, Nav, NavIcon } from "react-sidenav";

import { Icon } from "react-icons-kit";
import { ic_home as home } from "react-icons-kit/md/ic_home";
import { ic_reorder as simple } from "react-icons-kit/md/ic_reorder";
import { ic_donut_large as render } from "react-icons-kit/md/ic_donut_large";
import { fileEmpty as ic_aspect_ratio } from "react-icons-kit/icomoon/fileEmpty";
import { gear as settings } from "react-icons-kit/fa/gear";

const Text = styled.div`
  padding-left: 8px;
`;

const theme = {
  hoverBgColor: "#f5f5f5",
  selectionBgColor: "#b3bed1",
  selectionIconColor: "dark",
  color: "#000"
};

//const SideNav = withRR4();

export class AppNavigation extends React.Component {
    
    state = { selectedPath: "home" };

  onItemSelection = arg => {
    this.setState({ selectedPath: arg.path });
  };
  render() {
    return (        
      <SideNav theme={theme} defaultSelectedPath={"home"} onItemSelection={this.onItemSelection}>
        <Nav id="home">
          <NavIcon>
            <Icon icon={home} />
          </NavIcon>
          <Text>Home</Text>
        </Nav>
        <Nav id="blog">
        <NavIcon>
            <Icon icon={ic_aspect_ratio} />
        </NavIcon>
          <Text>
        Create New Blog</Text>
        </Nav>
        <Nav id="view">
          <NavIcon>
            <Icon icon={simple} />
          </NavIcon>
          <Text>View My Blogs</Text>
        </Nav>
        <Nav id="viewTop">
          <NavIcon>
            <Icon icon={render} />
          </NavIcon>
          <Text>View Top Blogs</Text>
        </Nav>
        <Nav id="settings">
          <NavIcon>
            <Icon icon={settings} />
          </NavIcon>
          <Text>Settings</Text>
        </Nav>
      </SideNav>
    );
  }
}
