import * as yup from "yup"
import {Field, Form, Formik} from "formik"
import {useHistory} from "react-router";
import {
    Avatar,
    Button,
    Container,
    CssBaseline,
    Grid,
    InputLabel,
    Link,
    makeStyles, MenuItem,
    TextField,
    Typography
} from "@material-ui/core";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import {signup} from "../../api/userApi";
import {Select} from "material-ui-formik-components/Select";



const useStyles = makeStyles((theme) => ({
    text: {
        "MuiInputBase-input MuiOutlinedInput-input": {
            borderBottom: "1px solid #fff",
        },
        "& .MuiInputBase-root MuiInput-root MuiInput-underline Mui-error Mui-error MuiInputBase-fullWidth MuiInput-fullWidth MuiInputBase-formControl MuiInput-formControl": {
            borderBottom: "1px solid #fff",
        }
    },
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%',
        marginTop: theme.spacing(3),
    },
    submit: {
        margin: theme.spacing(2, 0, 0),
    },
    clear: {
        margin: theme.spacing(1, 0, 2),
    }
}));

const validationSchema = yup.object().shape({
    username: yup.string()
        .min(3, "Too Short!")
        .max(20, "Too Long!")
        .required("The name is required"),
    lastname: yup.string()
        .min(3, "Too Short!")
        .max(20, "Too Long!")
        .required("The last name is required"),
    name: yup.string()
        .min(3, "Too Short!")
        .max(20, "Too Long!")
        .required("Email is required"),
    password: yup.string()
        .required("Please enter your password")
        .matches(
            /^.*(?=.{8,})((?=.*[!@#$%^&*()\-_=+{};:,<.>]){1})(?=.*\d)((?=.*[a-z]){1})((?=.*[A-Z]){1}).*$/,
            "Password must contain at least 8 characters, one uppercase, one number and one special case character"
        ),
    confirmPassword: yup.string()
        .required("Please confirm your password")
        .when("password", {
            is: password => (password && password.length > 0 ? true : false),
            then: yup.string().oneOf([yup.ref("password")], "Password doesn't match")
        })
});

const Signup = () => {
    const history = useHistory();
    const classes = useStyles();
    const handleOnSubmit = (formValues, formikHelpers) => {
        formikHelpers.setSubmitting(true);
        signup(formValues)
            .then(() => {
                history.push("/");
            })
            .finally(() => {
                formikHelpers.setSubmitting(false);
            })
    }
    return (
        <Formik
            initialValues={{
                username: '',
                lastname: '',
                name: '',
                password: '',
                confirmPassword: '',
                email: '',
                gender: '',
                age: '',
                weight: '',
                height: '',
                activityLevel: '',
                plan: '',

            }}
            onSubmit={handleOnSubmit}
            validationSchema={validationSchema}>
            {(props) => {
                const {
                    values,
                    touched,
                    errors,
                    handleBlur,
                    handleChange,
                    isSubmitting,
                    handleReset
                } = props
                return (
                    <>
                        <Container component="main" maxWidth="xs">
                            <CssBaseline/>
                            <div className={classes.paper}>
                                <Avatar className={classes.avatar}>
                                    <LockOutlinedIcon/>
                                </Avatar>
                                <Typography component="h1" variant="h5">
                                    Sign Up
                                </Typography>
                                <Form className={classes.form}>
                                    <Grid>
                                        <Grid container spacing={2}>
                                            <Grid item xs={12} sm={6}>
                                                <TextField
                                                    className={classes.text}
                                                    id='username'
                                                    label='User Name'
                                                    value={values.username}
                                                    onChange={handleChange}
                                                    onBlur={handleBlur}
                                                    helperText={touched.username ? errors.username : ""}
                                                    error={touched.username && Boolean(errors.username)}
                                                    margin="dense"
                                                    variant="outlined"
                                                    fullWidth
                                                    autoFocus
                                                />
                                            </Grid>
                                            <Grid item xs={12} sm={6}>
                                                <TextField
                                                    className={classes.text}
                                                    id='lastname'
                                                    label='Last Name'
                                                    value={values.lastname}
                                                    onChange={handleChange}
                                                    onBlur={handleBlur}
                                                    helperText={touched.lastname ? errors.lastname : ""}
                                                    error={touched.lastname && Boolean(errors.lastname)}
                                                    margin="dense"
                                                    variant="outlined"
                                                    fullWidth
                                                />
                                            </Grid>
                                        </Grid>
                                        <Grid item xs={12}>
                                            <TextField
                                                className={classes.text}
                                                id='name'
                                                label='Name'
                                                value={values.name}
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                helperText={touched.name ? errors.name : ""}
                                                error={touched.name && Boolean(errors.name)}
                                                margin="dense"
                                                variant="outlined"
                                                fullWidth
                                            />
                                        </Grid>
                                        <Grid item xs={12}>
                                            <TextField
                                                className={classes.text}
                                                id='password'
                                                label='Password'
                                                type="password"
                                                value={values.password}
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                helperText={touched.password ? errors.password : ""}
                                                error={touched.password && Boolean(errors.password)}
                                                margin="dense"
                                                variant="outlined"
                                                fullWidth
                                            />
                                        </Grid>
                                        <Grid item xs={12}>
                                            <TextField
                                                className={classes.text}
                                                id='confirmPassword'
                                                label='Confirm Password'
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                helperText={touched.confirmPassword ? errors.confirmPassword : ""}
                                                error={touched.confirmPassword && Boolean(errors.confirmPassword)}
                                                margin="dense"
                                                variant="outlined"
                                                type="password"
                                                fullWidth
                                            />
                                        </Grid>
                                        <Grid item xs={12} sm={6}>
                                            <TextField
                                                className={classes.text}
                                                id='email'
                                                label='email'
                                                value={values.email}
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                // helperText={touched.lastname ? errors.lastname : ""}
                                                error={touched.email && Boolean(errors.email)}
                                                margin="dense"
                                                variant="outlined"
                                                fullWidth
                                            />
                                        </Grid>
                                        <Grid container spacing={2}>
                                            <Grid item xs={12} sm={6}>
                                                <Field
                                                    id = 'gender'
                                                    value={values.gender}
                                                    onBlur={handleBlur}
                                                    fullWidth
                                                    name="gender"
                                                    label="Gender"
                                                    options={[
                                                        {value: 0, label: ""},
                                                        {value: "woman", label: "female"},
                                                        {value: "man", label: "male"},
                                                    ]}
                                                    component={Select}
                                                />
                                            </Grid>
                                            <Grid item xs={12} sm={6}>
                                                <TextField
                                                    className={classes.text}
                                                    id='age'
                                                    label='age'
                                                    value={values.age}
                                                    onChange={handleChange}
                                                    onBlur={handleBlur}
                                                    margin="dense"
                                                    variant="outlined"
                                                    fullWidth
                                                />
                                            </Grid>
                                        </Grid>
                                        <Grid container spacing={2}>
                                            <Grid item xs={12} sm={6}>
                                                <TextField
                                                    className={classes.numbers}
                                                    id='weight'
                                                    label='weight'
                                                    value={values.weight}
                                                    onChange={handleChange}
                                                    onBlur={handleBlur}
                                                    margin="dense"
                                                    variant="outlined"
                                                    fullWidth
                                                    autoFocus
                                                />
                                            </Grid>
                                            <Grid item xs={12} sm={6}>
                                                <TextField
                                                    className={classes.numbers}
                                                    id='height'
                                                    label='height'
                                                    value={values.height}
                                                    onChange={handleChange}
                                                    onBlur={handleBlur}
                                                    margin="dense"
                                                    variant="outlined"
                                                    fullWidth
                                                />
                                            </Grid>
                                        </Grid>
                                        <Grid container spacing={2}>
                                            <Grid item xs={12} sm={6}>
                                                <Field
                                                    id = 'activityLevel'
                                                    value={values.activityLevel}
                                                    onBlur={handleBlur}
                                                    fullWidth
                                                    name="activityLevel"
                                                    label="activityLevel"
                                                    options={[
                                                        {value: 0, label: ""},
                                                        {value: "sedentary", label: "sedentary"},
                                                        {value: "lightly", label: "lightly"},
                                                        {value: "moderately", label: "moderately"},
                                                        {value: "active", label: "active"},
                                                        {value: "very", label: "very"},
                                                    ]}
                                                    component={Select}
                                                />
                                            </Grid>
                                            <Grid item xs={12} sm={6}>
                                                <Field
                                                    id = 'plan'
                                                    value={values.plan}
                                                    onBlur={handleBlur}
                                                    fullWidth
                                                    name="plan"
                                                    label="plan"
                                                    options={[
                                                        {value: 0, label: ""},
                                                        {value: "lost10", label: "lost10"},
                                                        {value: "lost20", label: "lost20"},
                                                        {value: "gain1", label: "gain1"},
                                                        {value: "gain2", label: "gain2"},
                                                        {value: "same", label: "same"},
                                                    ]}
                                                    component={Select}
                                                />
                                            </Grid>
                                        </Grid>
                                    </Grid>
                                    <Grid item xs={12}>
                                        <Button
                                            type="submit"
                                            color="primary"
                                            className={classes.submit}
                                            variant="contained"
                                            disabled={isSubmitting}
                                            fullWidth
                                            margin="dense">
                                            SUBMIT
                                        </Button>
                                    </Grid>
                                    <Grid item xs={12}>
                                        <Button
                                            color="primary"
                                            variant="outlined"
                                            className={classes.clear}
                                            onClick={handleReset}
                                            fullWidth
                                            margin="dense">
                                            CLEAR
                                        </Button>
                                    </Grid>
                                    <Grid container justify="flex-end">
                                        <Grid item>
                                            <Link href="#" variant="body2">
                                                Already have an account? Sign in
                                            </Link>
                                        </Grid>
                                    </Grid>
                                </Form>
                            </div>
                        </Container>
                    </>
                )
            }}
        </Formik>
    )
}

export default Signup;
