import {saveInsuranceClient} from "./insuranceClient.js";
import {errorNotification, successNotification} from "./notification.js";
import axios from "axios";

export const getClientFile= async (id) => {
    try {
        return await axios.get(`http://localhost:8010/insuranceApp/${id}/file`)
    } catch (e) {
        throw e;
    }
}

export const updateClientFile = async (id, formData) => {
    try {
        return await axios.post(
            `http://localhost:8010/insuranceApp/${id}/file`,
            formData
        )
    } catch (e) {
        throw e;
    }
}

export const deleteClientFile = async (id) => {
    try {
        return await axios.delete(
            `http://localhost:8010/insuranceApp/file/${id}`
        )
    } catch (e) {
        throw e;
    }
}
