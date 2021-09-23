import {useEffect, useState} from "react";
import Api from "../api/Api";
import TechnicalComponentForm from "../application/TechnicalComponentForm"
import {Card, CardContent, Grid} from "@mui/material";
import ItemsList from "../ui/ItemsList";
import MetaForm from "../ui/MetaForm";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import {Breadcrumbs, Typography} from "@material-ui/core";
import {Link} from "react-router-dom";

const ComponentPage = (props) => {
    const [application, setApplication] = useState(null)
    const [component, setComponent] = useState(null)
    const componentId = props.match.params.componentId
    const applicationId = props.match.params.applicationId

    useEffect(() => {
        if (!application) {
            Api.getApplicationById(applicationId).then((result) => setApplication(result))
        }
        if (!component) {
            Api.getComponentById(applicationId,componentId).then((result) => setComponent(result))
        }
    }, [application, applicationId,componentId,component])

    if (component != null && application!=null) {
        const meta = []
        Object.keys(component.meta).forEach(function (key) {
            meta.push({key: key, value: component.meta[key]});
        })

        return (<div>

            <Breadcrumbs aria-label="breadcrumb">
                <Link underline="hover" color="inherit" to={"/application/"+applicationId}>
                    {application.name}
                </Link>
                <Typography color="text.primary">{component.name}</Typography>
            </Breadcrumbs>
            <br/>

            <Grid container spacing={3}>
                <Grid item xs={6}>
                    <Card>
                        <CardContent>
                            ID: {componentId}
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={6}>
                    <Card>
                        <CardContent>
                            Meta:
                            {meta.map((s) => {
                                return <div key={s.key}>{s.key} = {s.value} <DeleteForeverIcon color={"primary"} fontSize={"small"}></DeleteForeverIcon></div>
                            })}
                            <MetaForm applicationId={applicationId} componentId={componentId}></MetaForm>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item xs={12}>
                    <TechnicalComponentForm applicationId={applicationId} componentId={componentId}></TechnicalComponentForm>
                </Grid>

                <Grid item xs={12}>
                    <ItemsList header={"Technical Components"} type={"application/" + applicationId + "/component/"+componentId+"/technical-component"} data={component.technicalComponents}></ItemsList>
                </Grid>

            </Grid>
        </div>)
    } else {
        return (<div>Loading...</div>)
    }
}

export default ComponentPage;