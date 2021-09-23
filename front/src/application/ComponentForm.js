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
import AddBoxIcon from '@mui/icons-material/AddBox';

const ComponentForm = (props) => {
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
                <DialogTitle>New component</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        A component is a part of the application. Usually each of components has specific role in the application (e.g. authentication component).
                    </DialogContentText>
                    <TextField
                        fullWidth
                        label="Component's name"
                        variant="standard"
                        onChange={(event) => setName(event.target.value)}
                        value={name}/>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button
                        variant={"contained"}
                        onClick={() => Api.createComponent(props.applicationId, name).then(() => (window.location.reload()))}>
                        Save
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    )
}

export default ComponentForm