import {useEffect, useState} from "react";
import Api from "../api/Api";
import TechnicalComponentForm from "../application/TechnicalComponentForm"
import {Card, CardActions, CardContent, CardHeader, Grid} from "@mui/material";
import MetaForm from "../ui/MetaForm";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import {Breadcrumbs, Typography} from "@material-ui/core";
import {Link} from "react-router-dom";
import BadgeList from "../ui/BadgeList";
import DatasetLinkForm from "./DatasetLinkForm";

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
                        <CardHeader title={"General"} />
                        <CardContent>
                            ID: {componentId} <br/>
                            Name: {component.name} <br/>
                            Application: {application.name} <br/>
                            Number of technical components: {component.technicalComponents.length}<br/>
                            Number of datasets: {component.datasets.length}
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={6}>
                    <Card>
                        <CardHeader title={"Meta"} />
                        <CardContent>
                            {meta.map((s) => {
                                return <div key={s.key}>{s.key} = {s.value} <DeleteForeverIcon color={"primary"} fontSize={"small"}></DeleteForeverIcon></div>
                            })}
                        </CardContent>
                        <CardActions>
                            <MetaForm applicationId={applicationId} componentId={componentId}></MetaForm>
                        </CardActions>
                    </Card>
                </Grid>

                <Grid item xs={6}>
                    <Card>
                        <CardHeader title={"Technical components"} />
                        <CardContent>
                            <BadgeList mapKey={"id"} mapValue={"uri"} type={"application/" + applicationId + "/component/"+componentId+"/technical-component"} data={component.technicalComponents}></BadgeList>
                        </CardContent>
                        <CardActions>
                            <TechnicalComponentForm applicationId={applicationId} componentId={componentId}></TechnicalComponentForm>
                        </CardActions>
                    </Card>
                </Grid>

                <Grid item xs={6}>
                    <Card>
                        <CardHeader title={"Datasets"} />
                        <CardContent>
                            <BadgeList mapKey={"id"} mapValue={"name"} type={"dataset/"} data={component.datasets}></BadgeList>
                        </CardContent>
                        <CardActions>
                            <DatasetLinkForm defaultValues={component.datasets} applicationId={applicationId} componentId={componentId}></DatasetLinkForm>
                        </CardActions>
                    </Card>
                </Grid>

            </Grid>
        </div>)
    } else {
        return (<div>Loading...</div>)
    }
}

export default ComponentPage;