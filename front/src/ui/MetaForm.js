
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

const MetaForm = (props) => {
    const [key, setKey] = useState('')
    const [value, setValue] = useState('')
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
                <DialogTitle>Add meta-information</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Meta-information is a kind of notes. You can add any necessary information regarding this entity.
                    </DialogContentText>
                    <TextField size={"small"} label="Key" variant="standard"
                               onChange={(event) => setKey(event.target.value)} value={key}/>
                    <TextField size={"small"} label="Value" variant="standard"
                               onChange={(event) => setValue(event.target.value)} value={value}/>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button
                        size={"small"}
                        variant={"contained"}
                        onClick={() => {
                            if( props.technicalComponentId ){
                                Api.createMetaForTechnicalComponent(props.applicationId,props.componentId,props.technicalComponentId, key, value).then(() => (window.location.reload()))
                            }else if (props.componentId){
                                Api.createMetaForComponent(props.applicationId,props.componentId, key, value).then(() => (window.location.reload()))
                            }else if (props.applicationId){
                                Api.createMetaForApplication(props.applicationId, key, value).then(() => (window.location.reload()))
                            }
                        }}>
                        Save
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    )
}

export default MetaForm