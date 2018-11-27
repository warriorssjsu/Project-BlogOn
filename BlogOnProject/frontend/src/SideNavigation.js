import React from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { SideNav, Nav, NavIcon } from "react-sidenav";

import styled from "styled-components";
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


// Each logical "route" has two components, one for
// the sidebar and one for the main area. We want to
// render both of them in different places when the
// path matches the current URL.


function SidebarExample() {
  return (
    
      <SideNav theme={theme} defaultSelectedPath={"home"} >
        <Link to="/"><Nav id="home">
          <NavIcon>
            <Icon icon={home} />
          </NavIcon>
          <Text>Home</Text>
         </Nav>
        </Link>

        <Link to="/blogs/new">
        <Nav id="blog">
        <NavIcon>
            <Icon icon={ic_aspect_ratio} />
        </NavIcon>
          <Text>
        Create New Blog</Text>
        </Nav>
        </Link>

        <Link to="/blogs">
           <Nav id="view">
          <NavIcon>
            <Icon icon={simple} />
          </NavIcon>
          <Text>View My Blogs</Text>
        </Nav>
        </Link>

        <Link to="/topblogs">
        <Nav id="viewTop">
          <NavIcon>
            <Icon icon={render} />
          </NavIcon>
          <Text>View Top Blogs</Text>
        </Nav>
        </Link>

        <Link to="/allblogs">
        <Nav id="viewAll">
          <NavIcon>
            <Icon icon={render} />
          </NavIcon>
          <Text>View All Blogs</Text>
        </Nav>
        </Link>

        <Link to="/manageblogs">
        <Nav id="viewAll">
          <NavIcon>
            <Icon icon={render} />
          </NavIcon>
          <Text>Manage Blogs</Text>
        </Nav>
        </Link>

        <Link to="/settings">
        <Nav id="settings">
          <NavIcon>
            <Icon icon={settings} />
          </NavIcon>
          <Text>Settings</Text>
        </Nav>
        </Link>
      </SideNav>
    
  );
}

export default SidebarExample;