import React from "react";
import "./App.css";
import "./assets/bootstrap/css/bootstrap.min.css";
import "./assets/fonts/fontawesome-all.min.css";

import Matrix from '';
import { Container, Row, Col, Card } from "react-bootstrap";

function App() {
  return (
    <div className="App">
      <Matrix/>
      <Container>
        <Row className="justify-content-center">
          <Col className="col-md-9 col-lg-12 col-xl-10 justify-content-center">
            <Card className="shadow-lg o-hidden border-0 my-5 justify-content-center">
              <Card.Body className="p-0 justify-content-center">
                <Row className="justify-content-center">
                  <Col className="col-lg-6 justify-content-center">
                    <div className="p-5">
                      <div className="text-center">
                        <h4 className="text-dark mb-4">Secure Social</h4>
                      </div>
                      <a
                        className="btn btn-primary btn-block text-white btn-google btn-user"
                        role="button"
                      >
                        <i className="fab fa-google"></i>&nbsp; Login with
                        Google
                      </a>
                      <a
                        className="btn btn-primary btn-block text-white btn-facebook btn-user"
                        role="button"
                      >
                        <i className="fab fa-facebook-f"></i>&nbsp; Login with
                        Facebook
                      </a>
                    </div>
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

export default App;
