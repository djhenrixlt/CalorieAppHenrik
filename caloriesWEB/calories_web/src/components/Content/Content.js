import Container from "@material-ui/core/Container";
import {Route, Switch} from "react-router-dom";
import Login from "../../pages/Login/Login";
import Home from "../../pages/Home/Home";
import ClientForm from "../../pages/Client/ClientForm";
import QueueMonitoring from "../../pages/QueueMonitoring/QueueMonitoring";
import SignUp from "../../pages/SIgnUp/SignUp";

const Content = () => {
    return (
        <Container component="main" >
            <Switch>
                <Route exact path="/">
                    <Home/>
                </Route>
                <Route path="/login">
                    <Login/>
                </Route>
                <Route path="/signup">{/*private route*/}
                    <SignUp/>
                </Route>
                <Route path="/clientform">
                    <ClientForm/>
                </Route>
                <Route path="/waitinglist">
                    <QueueMonitoring/>
                </Route>
            </Switch>
        </Container>
    );
}

export default Content;
