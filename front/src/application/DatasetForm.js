import {useState} from "react";
import {Button, TextField} from "@material-ui/core";
import Api from "../api/Api";

const DatasetForm = (props) => {
    const [name, setName] = useState('')
    const [source, setSource] = useState('')

    return (
        <div>
            <TextField id="standard-basic" label="Name" variant="standard"
                       onChange={(event) => setName(event.target.value)} value={name}/>
            <TextField id="standard-basic" label="Source" variant="standard"
                       onChange={(event) => setSource(event.target.value)} value={source}/>
            <Button
                variant={"contained"}
                onClick={() => Api.createDataset(name, source).then(() => (window.location.reload()))}>
                Add new Dataset
            </Button>
        </div>
    )
}

export default DatasetForm