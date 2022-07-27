import {makeStyles, Typography} from "@material-ui/core";
import {Copyright} from "@material-ui/icons";
import {green} from "@material-ui/core/colors";

const useStyles = makeStyles((theme) => ({
    footer: {
    },
}));
const Footer = () => {
    const classes = useStyles();
    return (
        <>
            <footer className={classes.footer}>
                <Typography variant="h6" align="center" gutterBottom>
                    Simple Queue
                </Typography>
                <Typography variant="subtitle1" align="center" color="textSecondary" component="p">
                   Easy Queue for everyone!
                </Typography>
                <Copyright />
            </footer>
        </>
    );
}

export default Footer;