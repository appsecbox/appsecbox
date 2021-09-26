import {useEffect, useState} from "react";
import {
    Autocomplete,
    Button,
    Dialog, DialogActions,
    DialogContent, DialogContentText,
    DialogTitle,
    TextField
} from "@material-ui/core";
import Api from "../api/Api";
import AddLinkIcon from "@mui/icons-material/AddLink";

const DatasetLinkForm = (props) => {
    const [datasets, setDatasets] = useState(null)
    const [datasetsIds, setDatasetsIds] = useState([])
    const [open, setOpen] = useState(false);

    useEffect( ()=>{
        if( datasets===null ) {
            Api.getDatasets().then((r) => setDatasets(r))
        }
    }, [datasets, datasetsIds] )

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <div>
            <AddLinkIcon color={"primary"} cursor={"pointer"} onClick={handleClickOpen}></AddLinkIcon>

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Link datasets</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Connect the component with datasets
                    </DialogContentText>

                    <Autocomplete
                        multiple
                        size={"small"}
                        id="tags-standard-2"
                        options={datasets}
                        getOptionLabel={(option) => option.name}
                        disableCloseOnSelect
                        defaultValue={props.defaultValues}
                        onChange={(event, value) => {
                            let arrayOfIds = value.map( (v) => v.id)
                            setDatasetsIds(arrayOfIds)
                        }}
                        renderInput={(params) => (
                            <TextField
                                size={"small"}
                                {...params}
                                variant="standard"
                                label="Datasets used by this component"
                                placeholder="Pick a set of datasets"
                            />
                        )}
                    />

                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button
                        variant={"contained"}
                        size={"small"}
                        onClick={() => Api.linkDatasetsToComponent(props.applicationId,props.componentId,datasetsIds).then(() => (window.location.reload()))}>
                        Link
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    )
}

export default DatasetLinkForm