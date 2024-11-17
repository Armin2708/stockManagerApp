import axios from 'axios';

export const getInsuranceClients = async (pageNumber,pageSize) => {
    try {
        return await axios.get(`http://localhost:8010/insuranceApp/getAll?pageNumber=${pageNumber-1}`)
    }catch (e){
        throw e;
    }
}
export const getInsuranceClient= async (id) => {
    try {
        return await axios.get(`http://localhost:8010/insuranceApp/get/${id}`)
    } catch (e) {
        throw e;
    }
}

export const saveInsuranceClient = async (insuranceClient) => {
    try {
        return await axios.post(
                `http://localhost:8010/insuranceApp`,
            insuranceClient
        )
    } catch (e) {
        throw e;
    }
}

export const updateInsuranceClient = async (id, updateRequest) => {
    try {
        return await axios.put(
            `http://localhost:8010/insuranceApp/update/${id}`,
            updateRequest
        )
    } catch (e) {
        throw e;
    }
}

export const deleteInsuranceClient = async (id) => {
    try {
        return await axios.delete(
            `http://localhost:8010/insuranceApp/delete/${id}`
        )
    } catch (e) {
        throw e;
    }
}


