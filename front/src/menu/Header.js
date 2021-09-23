import {Link} from "react-router-dom";
import {Box, Button, Paper} from "@material-ui/core";
import AppsIcon from '@mui/icons-material/Apps';
import InsertChart from '@mui/icons-material/InsertChart';
import StorageIcon from '@mui/icons-material/Storage';

const Header = () => (
    <Paper elevation={1} square >
        <Box p={6} m={0}>
            <Button>
                <Link to={"/dashboard"}>
                    <InsertChart fontSize={"large"}></InsertChart>
                    <br />
                    Dashboard
                </Link>
            </Button>
            <Button>
                <Link to={"/application"}>
                    <AppsIcon fontSize={"large"}></AppsIcon>
                    <br />
                    Applications
                </Link>
            </Button>
            <Button>
                <Link to={"/dataset"}>
                    <StorageIcon fontSize={"large"}></StorageIcon>
                    <br />
                    Datasets
                </Link>
            </Button>
        </Box>
    </Paper>
)

export default Header;