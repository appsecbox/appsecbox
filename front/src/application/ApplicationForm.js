import {useState} from "react";
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    TextField
} from "@material-ui/core";
import Api from "../api/Api";
import AddBoxIcon from "@mui/icons-material/AddBox";

const ApplicationForm = (props) => {
    const [name, setName] = useState('')
    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <div>
            <AddBoxIcon cursor={"pointer"} color={"primary"}  onClick={handleClickOpen}></AddBoxIcon>


            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New application</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Application is a software which has clients, uses data and has user interface (not only graphic, API is a kind of interface).
                    </DialogContentText>
                    <TextField fullWidth label="App's name" variant="standard"
                               onChange={(event) => setName(event.target.value)} value={name}/>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button
                        variant={"contained"}
                        onClick={() => Api.createApplication(name).then(() => (window.location.reload()))}>
                        Save
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    )
}

export default ApplicationForm