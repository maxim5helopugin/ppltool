import React from "react";
import "./App.css";
import Dashboard from "./Components/Dashboard";
import Header from "./Components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./Components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div>
          <Header />
          <Route path="/dashboard" component={Dashboard} />
          <Route e xact path="/addProject" component={AddProject} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
