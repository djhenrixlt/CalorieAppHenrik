import {CssBaseline, makeStyles} from "@material-ui/core";
import {BrowserRouter as Router} from "react-router-dom";
import Header from "./components/Header/Header";
import Content from "./components/Content/Content";
import {Provider} from "react-redux";
import cover from './assets/cover.jpg'
import store from './store'

const useStyles = makeStyles((theme) => ({
    root: {
        minHeight: '100vh',
        backgroundImage: `url(${process.env.PUBLIC_URL + cover})`,
        backgroundRepeat: 'no-repeat',
        backgroundSize: 'cover',
    }
}))

function App() {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <CssBaseline />
            <Provider store={store}>
                <Router>
                    <Header/>
                    <Content/>
                </Router>
            </Provider>
        </div>
    );
}

export default App;
