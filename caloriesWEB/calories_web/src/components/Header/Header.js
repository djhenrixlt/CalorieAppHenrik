import {
    AppBar, Avatar,
    Button,
    Icon,
    makeStyles,
    Menu,
    MenuItem,
    SvgIcon,
    Toolbar,
    useMediaQuery,
    useTheme
} from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import SortIcon from "@material-ui/icons/Sort";
import {useState} from "react";
import useUser from "../../hooks/useUser";
import {NavLink} from "react-router-dom";
import {removeJwt, removeUserData} from "../../store/slices/userSlice";
import {useDispatch} from "react-redux";
import AllInclusiveOutlinedIcon from '@material-ui/icons/AllInclusiveOutlined';
import queue from '../../assets/SimplQnewlogo.png'

const menuItems = [
    {
        index: 0,
        title: "Waiting List",
        pageUrl: "/waitinglist"
    },
    {
        index: 1,
        title: "About",
        pageUrl: "/about"
    },
]

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        //textAlign: 'center',
        height: '10vh',
        fontFamily: 'Nunito',

    },
    appBar: {
        background: 'none',
    },
    appBarWrapper: {
        width: '80%',
        margin: '0 auto',
    },
    appBarTitle: {
        flexGrow: '1',
        fontSize: '2.5rem',
        color: 'pink',
        textDecoration: 'none'
    },
    icon: {
        color: '#fff',
        fontSize: '2rem',
    },
    colorTitle: {
        color: 'rgba(158, 216, 235, 1)',
    },
    headerButtons: {
        color: "red"
    },
    menuButton: {
        backgroundColor: 'rgba(158, 216, 235, 1)',
        '&:hover': {
            backgroundColor: 'rgba(215, 138, 248,  0.75)',
            color: '#795548',
        },
        '&:focus': {
            color: '#795548',
            backgroundColor: 'rgba(215, 138, 248,  0.75)',
        },

    },
    btColor: {
        fontFamily: 'Nunito',
        color: "#3A3768",
        fontWeight: "regular",
        fontSize: '0.85rem',
        '&:hover': {
            backgroundColor: 'rgba(215, 138, 248,  0.75)',
            color: '#795548',
        }
    },
    dropMenu: {
        color: "#795548",
    },
    dropMenuText: {
        color: "#795548",
    },
    avatar: {
        backgroundColor: 'rgba(158, 216, 235, 1)',
        },
    avatar1: {
        background: `transparent url${queue} 0% 0% no-repeat padding-box`,
        opacity: 1,
    }

}))

const Header = () => {
    const user = useUser()
    const classes = useStyles();
    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);
    const theme = useTheme();
    const isMobile = useMediaQuery(theme.breakpoints.down("sm"))
    const dispatch = useDispatch()

    const logout = () => {
        dispatch(removeJwt())
        dispatch(removeUserData())
    }

    const handleMenu = event => {
        setAnchorEl(event.currentTarget);
    }
    const handleMenuClick = () => {
        setAnchorEl(null);
    }

    return (
        <div className={classes.root} id='header'>
            <AppBar className={classes.appBar} elevation={0}>
                <Toolbar className={classes.appBarWrapper}>
                    <Icon className={classes.avatar1}>
                    </Icon>
                    <Avatar className={classes.avatar}>
                        <AllInclusiveOutlinedIcon/>
                    </Avatar>

                    <NavLink exact to="/" className={classes.appBarTitle} >
                        <h1 className={classes.appBarTitle} >Specialist
                            <span className={classes.colorTitle}>Queue</span>
                        </h1>
                    </NavLink>
                    {isMobile ? (
                        <>
                            <IconButton
                                edge="start"
                                className={classes.menuButton}
                                aria-label="menu"
                                onClick={handleMenu}
                            >
                                <SortIcon className={classes.icon}/>
                            </IconButton>
                            <Menu
                                id="menu-appbar"
                                anchorEl={anchorEl}
                                anchorOrigin={{
                                    vertical: "top",
                                    horizontal: "right"
                                }}
                                keepMounted
                                transformOrigin={{
                                    vertical: "top",
                                    horizontal: "right",
                                }}
                                open={open}
                                onClose={() => setAnchorEl(null)}
                            >
                                {menuItems.map(({title, pageUrl, index}) => {
                                    // const {title, pageUrl} = item;
                                    return (
                                        <MenuItem className={classes.dropMenu} >
                                            <NavLink
                                                key={index}
                                                className={classes.dropMenuText}
                                                to={pageUrl}
                                                onClick={() => handleMenuClick()}>
                                                {title}
                                            </NavLink>
                                        </MenuItem>
                                    )
                                })}
                                {
                                    !!user ? (//!! pavercia i boolean javascript
                                        <>
                                            <MenuItem className={classes.dropMenu}>
                                                <NavLink
                                                    className={classes.dropMenuText}
                                                    to="/home"
                                                    component={Button}
                                                    onClick={logout}>
                                                    Logout
                                                </NavLink>
                                            </MenuItem>
                                        </>
                                    ) : (
                                        <>
                                            <MenuItem className={classes.dropMenu}>
                                                <NavLink to="/login" className={classes.dropMenuText}>Login</NavLink>
                                            </MenuItem>
                                            <MenuItem className={classes.dropMenu}>
                                                <NavLink to="/signup" className={classes.dropMenuText}>Sign Up</NavLink>
                                            </MenuItem>
                                        </>
                                    )
                                }
                            </Menu>
                        </>
                    ) : (
                        <>
                            <div className={classes.headerButtons}>
                                {menuItems.map(({title, pageUrl, index}) => {
                                    return (
                                        <>
                                            <Button
                                                key={index}
                                                className={classes.btColor}
                                                component={NavLink}
                                                to={pageUrl}>
                                                {title}
                                            </Button>
                                        </>
                                    )
                                })}
                                {
                                    !!user ? (//!! pavercia i boolean javascript
                                        <>
                                            <Button
                                                className={classes.btColor}
                                                to="/home"
                                                component={NavLink}
                                                onClick={logout}>
                                                Logout
                                            </Button>
                                        </>
                                    ) : (
                                        <>
                                            <Button to="/login" className={classes.btColor}
                                                    component={NavLink}>Login</Button>
                                            <Button to="/signup" className={classes.btColor} component={NavLink}>Sign
                                                Up</Button>
                                        </>
                                    )
                                }
                            </div>
                        </>
                    )}

                </Toolbar>
            </AppBar>
        </div>
    );
}

export default Header;