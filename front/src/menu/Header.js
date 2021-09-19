import {Link} from "react-router-dom";
import {Box, Button, Menu, Paper} from "@material-ui/core";

const Header = () => (
    <Paper elevation={1} square >
        <Box p={6} m={0}>
            <Button>
                <Link to={"/dashboard"}>Dashboard</Link>
            </Button>
            <Button>
                <Link to={"/application"}>Applications</Link>
            </Button>
        </Box>
    </Paper>
)

export default Header;