import {useEffect, useState} from "react";
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
import AddBoxIcon from "@mui/icons-material/AddBox";

const DatasetForm = (props) => {
    const [name, setName] = useState('')
    const [source, setSource] = useState('')
    const [categories, setCategories] = useState(null)
    const [selectedCategories, setSelectedCategories] = useState([])

    const [open, setOpen] = useState(false);
    const handleClickOpen = () => {setOpen(true);};
    const handleClose = () => {setOpen(false);};

    useEffect( ()=>{
        if( categories===null ) {
            Api.getDatasetCategories().then((r) => setCategories(r))
        }
    }, [categories] )

    return (
        <div>
            <AddBoxIcon cursor={"pointer"} color={"primary"}  onClick={handleClickOpen}></AddBoxIcon>

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New dataset</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Dataset is a set of data that are using in some components, e.g. credentials, personal data, bank data, etc.
                    </DialogContentText>
                    <TextField
                        fullWidth
                        label="Name"
                        variant="standard"
                        onChange={(event) => setName(event.target.value)}
                        value={name}/>
                    <TextField
                        fullWidth
                        label="Source"
                        variant="standard"
                        onChange={(event) => setSource(event.target.value)}
                        value={source}/>
                    <Autocomplete
                        multiple
                        size={"small"}
                        id="tags-standard-3"
                        options={categories}
                        getOptionLabel={(option) => option}
                        disableCloseOnSelect
                        onChange={(event, value) => {
                            let arrayOfIds = value.map( (v) => v )
                            setSelectedCategories(arrayOfIds)
                        }}
                        renderInput={(params) => (
                            <TextField
                                size={"small"}
                                {...params}
                                variant="standard"
                                label="Dataset categories"
                                placeholder="Mark the dataset via categories"
                            />
                        )}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button
                        size={"small"}
                        variant={"contained"}
                        onClick={() => Api.createDataset(name,source,selectedCategories).then(() => (window.location.reload()))}>
                        Save
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    )
}

export default DatasetForm