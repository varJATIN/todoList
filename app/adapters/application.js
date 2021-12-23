import JSONAPIAdapter from '@ember-data/adapter/json-api';

export default JSONAPIAdapter.extend({
  /*give the base api/host of the server for CRUD, deafult same as ember-server
   endpoint*/ 
  host: 'http://localhost:5000',
  headers: {
    'Access-Control-Allow-Origin': true,
  },
});
