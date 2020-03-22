import React from "react";
import Modal from "react-modal";
import * as firebase from "firebase";

import { Redirect } from "react-router-dom";
import { Container, Col, Row, Card, Button, ListGroup } from "react-bootstrap";

const customStyles = {
  content: {
    top: "50%",
    left: "50%",
    width: "40%",
    right: "auto",
    bottom: "auto",
    marginRight: "-50%",
    transform: "translate(-50%, -50%)"
  }
};

class Contacts extends React.Component {
  constructor(props) {
    super(props);
    this.state = this.props.location.state;

    this.handleCloseModal = this.handleCloseModal.bind(this);
    this.searchForContact = this.searchForContact.bind(this);
    this.handleChange = this.handleChange.bind(this);

    if (!firebase.apps.length) {
      firebase.initializeApp({
        apiKey: process.env.REACT_APP_firebase_api_key,
        authDomain: "crest-f8474.firebaseapp.com",
        databaseURL: "https://crest-f8474.firebaseio.com",
        projectId: "crest-f8474",
        storageBucket: "crest-f8474.appspot.com",
        messagingSenderId: "229758778420",
        appId: "1:229758778420:web:3c4cfcf53a4634a13d1e61",
        measurementId: "G-DC2XXBEFJK"
      });
    }
  }

  componentDidMount() {
    this.setState({ showModal: false, value: "" });

    var db = firebase.firestore();
    var self = this;
    var people = [];

    db.collection("users")
      .get()
      .then(res => {
        res.docs.map(doc => {
          let fDoc = db
            .collection("users")
            .doc(doc.id)
            .get()
            .then(res => {
              if (doc.id !== this.state.userUid) {
                let person = res.data();
                people.push({'person': person, 'id': doc.id});
              }
            });
          return fDoc;
        });
      })
      .then(() => {
        self.setState({ people: people});
      });
  }

  handleChange = event => {
    this.setState({ value: event.target.value });
  };

  handleCloseModal() {
    this.setState({ showModal: false });
  }

  searchForContact = event => {
    event.preventDefault();
    console.log(this.state.value);
  };

  addFriend = userID => {
    console.log(userID)
    //Add friend to user, state: pending
    var db = firebase.firestore();

    let friends = this.state.friends
    friends.push({'id': userID, 'state': 'pending'})
    let data = {
      displayName: this.state.userName,
      photoURL: this.state.userPhoto,
      email: this.state.userEmail,
      friends: friends
    };

    console.log(friends)
    db.collection('users').doc(this.state.userUid)
      .set(data)

    db.collection('users').doc(userID)
      .get()
      .then(res => res.data())
      .then(doc => {
        console.log(doc.friends)
        let test = doc.friends
        test.push({'id': this.state.userUid, 'state': 'requested'})
        console.log(test)

        doc.friends = test
        db.collection('users').doc(userID)
        .set(doc)
      })
  }

  render() {
    Modal.setAppElement("#root");

    const {
      userEmail,
      userName,
      userPhoto,
      userToken,
      userUid,
      loadProfile,
      friends,
      people
    } = this.state;

    return (
      <div id="wrapper">
        {loadProfile && (
          <Redirect
            to={{
              pathname: "/profile",
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
                <div
                  className="nav-link"
                  onClick={() => this.setState({ loadProfile: true })}
                >
                  <i className="fas fa-user-lock"></i>
                  <span>Profile</span>
                </div>
              </li>
              <li className="nav-item" role="presentation">
                <div className="nav-link active">
                  <i className="fas fa-user"></i>
                  <span>Contacts</span>
                </div>
              </li>
            </ul>
            <div className="text-center d-none d-md-inline"></div>
          </Container>
        </nav>
        <Col className="d-flex flex-column">
          <Container fluid={true}>
            <h3 className="text-dark mb-4">Contacts</h3>
            <Row className="row mb-3">
              <Col className="col">
                <Row className="row">
                  <Col className="col">
                    <Card className="card shadow">
                      <Card.Header>
                        <Row>
                          <Col
                            style={{
                              paddingTop: 0,
                              paddingBottom: 0,
                              marginBottom: 0
                            }}
                          >
                            <h5 className="text-dark mt-2">Contacts</h5>
                          </Col>
                          <Col className="justify-content-right pull-right">
                            <Button
                              className="btn-primary pull-right float-right"
                              onClick={() => this.setState({ showModal: true })}
                            >
                              Add Contact
                            </Button>
                            <Modal
                              isOpen={this.state.showModal}
                              contentLabel="Add Contact"
                              style={customStyles}
                            >
                              <h4>Add Contact</h4>
                              <hr />
                              <form
                                className="form-inline d-none d-sm-inline-block mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
                                style={{width:"95%"}}
                                onSubmit={this.searchForContact}
                              >
                                <div className="input-group">
                                  <input
                                    id="username"
                                    type="text"
                                    className="bg-light form-control border-0 small"
                                    placeholder="Search for Contact..."
                                    value={this.state.value}
                                    onChange={this.handleChange}
                                  />
                                  <div className="input-group-append">
                                    <Button
                                      className="btn btn-primary py-0"
                                      type="submit"
                                      value="Send"
                                    >
                                      <i className="fas fa-search"></i>
                                    </Button>
                                  </div>
                                </div>
                              </form>
                              <hr />
                              <ListGroup>
                                { people && 
                                  people.map(({person, id}) => (
                                      <ListGroup.Item key={id} onClick={() => this.addFriend(id)}>
                                        <Row>
                                          <Col>
                                            <img src={person.photoURL} alt="Profile" style={{width:"32px", height:"auto"}}/>
                                          </Col>
                                          <Col>
                                            {person.displayName}
                                          </Col>
                                          <Col>
                                            {person.email}
                                          </Col>
                                        </Row>
                                        </ListGroup.Item>
                                  ))
                                }
                              </ListGroup>
                              <hr />
                              <Button onClick={this.handleCloseModal}>
                                close
                              </Button>
                            </Modal>
                          </Col>
                        </Row>
                      </Card.Header>
                      <Card.Body>Contacts Component</Card.Body>
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

export default Contacts;
