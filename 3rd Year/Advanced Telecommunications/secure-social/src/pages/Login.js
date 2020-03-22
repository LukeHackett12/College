import logo from "../assets/img/logo.png";

import React from "react";
import { Redirect } from "react-router-dom";
import * as firebase from "firebase";

import { Container, Row, Col, Card } from "react-bootstrap";
import { GoogleLogin } from "react-google-login";
import FacebookLogin from "react-facebook-login";

class Login extends React.Component {
  constructor(props) {
    super(props);

    require('dotenv').config({debug: true})

    this.state = ({
      loggedIn: false,
      friends: [],
      userUid: null,
      userName: null,
      userPhoto: null,
      userEmail: null,
      userToken: null
    });

    if (!firebase.apps.length) {
      console.log(process.env.REACT_APP_firebase_api_key)

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
  loadProfile = creds => {
    console.log(creds);

    var self = this;
    var db = firebase.firestore();

    let userDoc = db.collection("users").doc(creds.user.uid);
    userDoc
      .get()
      .then(doc => {
        if (!doc.exists) {
          let data = {
            displayName: creds.user.displayName,
            photoURL: creds.user.photoURL,
            email: creds.user.email,
            friends: []
          };

          db.collection("users")
            .doc(creds.user.uid)
            .set(data)
            .then(() =>
              self.setState({
                loggedIn: true,
                userUid: creds.user.uid,
                userName: creds.user.displayName,
                userPhoto: creds.user.photoURL,
                userEmail: creds.user.email,
                userToken: creds.credential.idToken,
                friends: []
              })
            );
        } else {
          console.log("Document data:", doc.data());
          self.setState({
            loggedIn: true,
            userUid: creds.user.uid,
            userName: creds.user.displayName,
            userPhoto: creds.user.photoURL,
            userEmail: creds.user.email,
            userToken: creds.credential.idToken,
            friends: doc.data().friends
          });
        }
      })
      .catch(err => {
        console.log("Error getting document", err);
      });
  };

  responseGoogle = response => {
    console.log(response);
    var credential = firebase.auth.GoogleAuthProvider.credential(
      response.uc.id_token
    );

    var self = this;

    firebase
      .auth()
      .signInWithCredential(credential)
      .then(creds => self.loadProfile(creds))
      .catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        // The email of the user's account used.
        var email = error.email;
        // The firebase.auth.AuthCredential type that was used.
        var credential = error.credential;

        console.log("Error " + errorCode + ": " + errorMessage);
        console.log("Email: " + email);
        console.log("Credential: " + credential);
      });
  };

  responseFacebook = response => {
    console.log(response);

    var credential = firebase.auth.FacebookAuthProvider.credential(
      response.accessToken
    );

    var self = this;

    firebase
      .auth()
      .signInWithCredential(credential)
      .then(creds => self.loadProfile(creds))
      .catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        // The email of the user's account used.
        var email = error.email;
        // The firebase.auth.AuthCredential type that was used.
        var credential = error.credential;

        console.log("Error " + errorCode + ": " + errorMessage);
        console.log("Email: " + email);
        console.log("Credential: " + credential);
      });
  };

  render() {
    const {
      loggedIn,
      userUid,
      userName,
      userPhoto,
      userEmail,
      userToken,
      friends
    } = this.state;

    return (
      <div className="App">
        {loggedIn && (
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

        <Container>
          <Row className="justify-content-center">
            <Col className="col-md-9 col-lg-12 col-xl-10 justify-content-center">
              <Card className="shadow-lg o-hidden border-0 my-5 justify-content-center">
                <Card.Body className="p-0 justify-content-center">
                  <Row className="justify-content-center">
                    <Col className="col-lg-6 justify-content-center">
                      <div className="p-5 justify-content-center">
                        <div className="text-center justify-content-center">
                          <h4 className="text-dark mb-4">Secure Social</h4>
                        </div>
                        <GoogleLogin
                          clientId="229758778420-kfodakij699oncpbvaoppge2hj56oe6f.apps.googleusercontent.com"
                          render={renderProps => (
                            <button
                              onClick={renderProps.onClick}
                              disabled={renderProps.disabled}
                              className="btn btn-primary btn-block text-white btn-google btn-user"
                            >
                              <i className="fab fa-google"></i>&nbsp; Login with
                              Google
                            </button>
                          )}
                          onSuccess={this.responseGoogle}
                          onFailure={this.responseGoogle}
                          cookiePolicy={"single_host_origin"}
                        />
                        <FacebookLogin
                          appId="649543218885961"
                          autoLoad={false}
                          cssClass="btn btn-primary btn-block text-white btn-facebook btn-user"
                          icon="fa-facebook"
                          style={{ textSpacing: "10px" }}
                          callback={this.responseFacebook}
                        />
                      </div>
                    </Col>
                    <Col className="col-lg-6 justify-content-center">
                      <img
                        src={logo}
                        alt="logo"
                        style={{
                          backgroundColor: "transparent",
                          paddingTop: "5vh",
                          height: "30vh"
                        }}
                      />
                    </Col>
                  </Row>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default Login;
