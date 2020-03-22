import React from "react";
import { Redirect } from "react-router-dom";
import * as firebase from "firebase";

import { Container, Col, Row, Card } from "react-bootstrap";

class Profile extends React.Component {
  constructor(props) {
    super(props);
    this.state = this.props.location.state;

    let db = firebase.firestore()
    let doc = db.collection('cities').doc(this.state.userUid);

    let observer = doc.onSnapshot(docSnapshot => {
      console.log(`Received doc snapshot: ${docSnapshot}`);
    }, err => {
      console.log(`Encountered error: ${err}`);
    });

    observer();
  }

  render() {
    const {
      userEmail,
      userName,
      userPhoto,
      userToken,
      userUid,
      loadContacts,
      friends
    } = this.state;

    return (
      <div id="wrapper">
        {loadContacts && (
          <Redirect
            to={{
              pathname: "/contacts",
              state: {
                userUid: userUid,
                userName: userName,
                userPhoto: userPhoto,
                userEmail: userEmail,
                userToken: userToken,
                friends: friends
              }
            }}
          />
        )}

        <nav className="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
          <Container
            fluid={true}
            className="container-fluid d-flex flex-column p-0"
          >
            <div className="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0">
              <div className="sidebar-brand-icon rotate-n-15">
                <i className="fas fa-laugh-wink"></i>
              </div>
              <div className="sidebar-brand-text mx-3">
                <span>Secure Social</span>
              </div>
            </div>
            <hr className="sidebar-divider my-0" />
            <ul className="nav navbar-nav text-light" id="accordionSidebar">
              <li className="nav-item" role="presentation">
                <div className="nav-link active">
                  <i className="fas fa-user"></i>
                  <span>Profile</span>
                </div>
              </li>
              <li className="nav-item" role="presentation">
                <div
                  className="nav-link"
                  onClick={() => this.setState({ loadContacts: true })}
                >
                  <i className="fas fa-user-lock"></i>
                  <span>Contacts</span>
                </div>
              </li>
            </ul>
            <div className="text-center d-none d-md-inline"></div>
          </Container>
        </nav>
        <Col className="d-flex flex-column">
          <Container fluid={true}>
            <h3 className="text-dark mb-4">Profile</h3>
            <Row className="row mb-3">
              <Col className="col-lg-4">
                <Card className="card mb-3">
                  <Card.Body className="text-center shadow">
                    <img
                      className="rounded-circle mb-3 mt-4"
                      src={userPhoto}
                      alt="Profile"
                      width="160"
                      height="160"
                    />
                  </Card.Body>
                </Card>
                <Card className="card shadow mb-4">
                  <Card.Header className="card-header py3">
                    <h6 className="text-primary font-weight-bold m-0">
                      Profile Information
                    </h6>
                  </Card.Header>
                  <Card.Body>
                    <Row>Name: {userName}</Row>
                    <Row>Email Address: {userEmail}</Row>
                    <Row>Friends: {friends.length}</Row>
                  </Card.Body>
                </Card>
              </Col>
              <Col className="col-lg-8">
                <Row className="row">
                  <Col className="col">
                    <Card className="card shadow mb-3">
                      <Card.Header>Messages</Card.Header>
                      <Card.Body>Messages Component</Card.Body>
                    </Card>
                  </Col>
                </Row>
              </Col>
            </Row>
          </Container>
        </Col>
      </div>
    );
  }
}

export default Profile;
