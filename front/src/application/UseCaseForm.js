import {useState} from "react";
import {
    Autocomplete,
    Button,
    Dialog, DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    TextField
} from "@material-ui/core";
import Api from "../api/Api";
import AddBoxIcon from '@mui/icons-material/AddBox';

const UseCaseForm = (props) => {
    const [name, setName] = useState('')
    const [actor, setActor] = useState('')
    const [action, setAction] = useState('')
    const [componentsIds, setComponentsIds] = useState([])

    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };


    const top100Films = props.components

    return (
        <div>
            <AddBoxIcon cursor={"pointer"} color={"primary"}  onClick={handleClickOpen}></AddBoxIcon>

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New use-case</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        A use case is a list of actions or event steps typically defining the interactions between a role (known in the Unified Modeling Language (UML) as an actor) and a system to achieve a goal. (Wiki)
                    </DialogContentText>
                    <TextField fullWidth
                               size={"small"}
                               label="Name"
                               variant="standard"
                               onChange={(event) => setName(event.target.value)}
                               value={name}/>
                    <TextField fullWidth
                               size={"small"}
                               label="Actor"
                               variant="standard"
                               onChange={(event) => setActor(event.target.value)}
                               value={actor}/>
                    <TextField multiline
                               fullWidth
                               maxRows={10}
                               rows={4}
                               size={"small"}
                               label="Action"
                               variant="standard"
                               onChange={(event) => setAction(event.target.value)}
                               value={action}/>
                    <Autocomplete
                        multiple
                        size={"small"}
                        id="tags-standard"
                        options={top100Films}
                        getOptionLabel={(option) => option.name}
                        disableCloseOnSelect
                        onChange={(event, value) => {
                            let arrayOfIds = value.map( (v) => v.id)
                            setComponentsIds(arrayOfIds)
                        }}
                        renderInput={(params) => (
                            <TextField
                                size={"small"}
                                {...params}
                                variant="standard"
                                label="Components involved in this case"
                                placeholder="Pick a set of using components"
                            />
                        )}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button
                        size={"small"}
                        variant={"contained"}
                        onClick={() => Api.createUseCase(props.applicationId,name,actor,action,componentsIds).then(() => (window.location.reload()))}>
                        Save
                    </Button>
                </DialogActions>
            </Dialog>

        </div>
    )
}

export default UseCaseForm