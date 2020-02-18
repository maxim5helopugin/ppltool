import { GET_ERRORS } from "../Actions/Types";

const initialState = {};

export default function(state = initialState, Action) {
  switch (Action.type) {
    case GET_ERRORS:
      return Action.payload;
    default:
      return state;
  }
}
