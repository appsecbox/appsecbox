import {useEffect, useState} from "react";
import {
    Button,
    Dialog, DialogActions,
    DialogContent, DialogContentText,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField
} from "@material-ui/core";
import Api from "../api/Api";
import AddBoxIcon from "@mui/icons-material/AddBox";

const TechnicalComponentForm = (props) => {
    const [uri, setUri] = useState('')
    const [type, setType] = useState('')
    const [types, setTypes] = useState(null)
    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };


    useEffect(()=>{
        if(!types){
            Api.getControls().then( (r) => setTypes(r))
        }
    }, [types])

    return (
        <div>
            <AddBoxIcon cursor={"pointer"} color={"primary"}  onClick={handleClickOpen}></AddBoxIcon>

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New technical component</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        A technical components is a thing that relates to component, e.g. source code or server.
                    </DialogContentText>
                    <TextField fullWidth size={"small"} label="URI" variant="standard"
                               onChange={(event) => setUri(event.target.value)} value={uri}/>
                    <FormControl fullWidth>
                        <InputLabel id="demo-simple-select-label">Type</InputLabel>
                        <Select
                            fullWidth
                            size={"small"}
                            labelId="demo-simple-select-label"
                            id="demo-simple-select"
                            value={type}
                            placeholder={"Select..."}
                            label="Type"
                            onChange={(event)=>setType(event.target.value)}
                        >
                            {
                                types===null?
                                    '':
                                    types.map( (t) => {
                                        return (<MenuItem value={t}>{t}</MenuItem>)
                                    })
                            }
                        </Select>
                    </FormControl>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button
                        variant={"contained"}
                        size={"small"}
                        onClick={() => Api.createTechnicalComponent(props.applicationId,props.componentId,uri,type).then(() => (window.location.reload()))}>
                        Save
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    )
}

export default TechnicalComponentForm